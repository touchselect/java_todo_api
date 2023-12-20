'use strict';

$(document).ready(function() {
    // Handlebarsのヘルパー関数を定義
    Handlebars.registerHelper('eq', function (value1, value2) {
        return value1 === value2;
    });
});


// ! モデル
let Todo = Backbone.Model.extend({
    defaults: {
        id: null,
        title: '',
        status: '未着手',
        details: ''
    },
    urlRoot: '/todos',

    deleteTodo: function(){
        this.destroy({
            success: function(model, response){
                console.log('Todo deleted:', model);
                Backbone.history.navigate('', {trigger: true});
            },
            error: function(model, response){
                console.error('Error while deleting Todo:', response);
            }
        });
    }
});


// ! コレクション
let TodoCollection = Backbone.Collection.extend({
    model: Todo,
    url: '/todos'
});


// ! ビュー
// * Todo一覧ビュー
let TodoView = Backbone.View.extend({
    tagName: 'li',
    className: 'list-group-item',
    template: Handlebars.compile(todoTemplate),
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));

        let status = this.model.get('status');
        if (status === '未着手') {
            this.$el.addClass('bg-danger-subtle');
        } else if (status === '進行中') {
            this.$el.addClass('bg-warning-subtle');
        } else if (status === '完了') {
            this.$el.addClass('bg-success-subtle');
        }
        return this;
    }
});

// * Todo追加フォーム
let TodoAddView = Backbone.View.extend({
    el: '#content', // フォームを表示する要素のセレクタ
    template: Handlebars.compile(todoFormTemplate), // テンプレートの読み込み

    events: {
        'submit #todo-form': 'submitForm'
    },

    render: function() {
        $('#add-todo-link').hide();
        $('#todo-list').empty(); // Todoリストをクリア
        this.$el.html(this.template()).hide().slideDown('slow'); // テンプレートを要素にレンダリング
        return this;
    },

    submitForm: function(e) {
        e.preventDefault();

        let newTodo = new Todo({
            title: this.$('#todo-title').val().trim(),
            details: this.$('#todo-details').val().trim(),
            status: '未着手'
        });

        newTodo.save(null, {
            success: function(model, response) {
                console.log('New Todo added:', model);
                Backbone.history.navigate('', { trigger: true }); // ホームにリダイレクト
            },
            error: function(model, response) {
                console.error('Error while adding new Todo:', response);
            }
        });
    }
});

// * Todo詳細ビュー
let TodoDetailView = Backbone.View.extend({
    el: '#content',
    template: Handlebars.compile(todoDetailTemplate),

    events: {
        'click .delete-todo': 'deleteTodo'
    },

    render: function(){
        $('#add-todo-link').hide();
        $('#todo-list').empty(); // Todoリストをクリア
        this.$el.html(this.template(this.model.toJSON())).hide().slideDown('slow');
        return this;
    },

    deleteTodo: function(){
        if(confirm('Are you sure want to delete this todo?')){
            console.log(this.model.id);
            this.model.deleteTodo();
        }
    }
});

// * Todo編集ビュー
let TodoEditView = Backbone.View.extend({
    el: '#content',
    template: Handlebars.compile(todoEditTemplate),

    events: {
        'submit #todo-edit-form': 'submitForm'
    },

    render: function() {
        this.$el.html(this.template(this.model.toJSON())).hide().slideDown('slow');
        return this;
    },

    submitForm: function(e){
        e.preventDefault();

        this.model.set({
            title: this.$('#todo-title').val().trim(),
            details: this.$('#todo-details').val().trim(),
            status: this.$('#todo-status').val()
        });

        this.model.save(null, {
            success: function(model, response){
                console.log('Todo updated:', model);
                Backbone.history.navigate('', {trigger: true});
            },
            error: function(model, response){
                console.error('Error while updating Todo:' , response);
            }
        });
    }
});


// ! ルーティング
let AppRouter = Backbone.Router.extend({
    // ? ルーティング
    routes: {
        '': 'home',
        'add-todo': 'addTodo',
        'todos/:id': 'viewTodo',
        'todos/edit/:id': 'editTodo'
    },

    // ? Todo一覧
    home: function() {
        $('#content').empty(); // Todo追加フォームをクリア
        $('#add-todo-link').show();
        let todos = new TodoCollection();
        todos.fetch({
            success: function(collection) {
                let todoList = $('#todo-list');
                todoList.empty();

                collection.each(function(todo) {
                    let view = new TodoView({model: todo});
                    todoList.append(view.render().el);
                });
            },
            error: function() {
                console.error('Failed to fetch todos.');
            }
        });
    },

    // ? Todo追加フォーム
    addTodo: function() {
        let todoAddView = new TodoAddView();
        todoAddView.render();
    },

    // ? Todo詳細
    viewTodo: function(id) {
        let todo = new Todo({id: id});
        todo.fetch({
            success: function(model) {
                let todoDetailView = new TodoDetailView({model: model});
                todoDetailView.render();
            },
            error: function() {
                console.error('Failed to fetch todo with id:', id);
            }
        });
    },

    // ? Todo編集
    editTodo: function(id) {
        let todo = new Todo({id: id});
        todo.fetch({
            success: function(model) {
                let todoEditView = new TodoEditView({model: model});
                todoEditView.render();
            },
            error: function() {
                console.error('Failed to fetch todo for edit with id:', id);
            }
        });
    }

});



$(function() {
    new AppRouter();
    Backbone.history.start();
});

