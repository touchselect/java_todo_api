const todoTemplate = `
<li class="list-group-item d-flex justify-content-between align-items-center">
    <div>
        <h5 class="mb-1">{{title}}</h5>
        <small>{{status}}</small>
    </div>
    <div>
        <a href="#todos/{{id}}">View Details</a>
    </div>
</li>
`;

const todoFormTemplate = `
<form id="todo-form" class="form">
    <div class="form-group m-3">
        <label for="todo-title">Title:</label>
        <input type="text" class="form-control" id="todo-title" placeholder="Enter Todo Title" required>
    </div>
    <div class="form-group m-3">
        <label for="todo-details">Details:</label>
        <input type="text" class="form-control" id="todo-details" placeholder="Enter Details">
    </div>
    <button type="submit" class="btn btn-primary">Add Todo</button>
    <a href="#" class="btn btn-secondary my-2">Back to List</a>
</form>
`;

const todoDetailTemplate = `
<div class="card">
    <div class="card-body">
        <h5 class="card-title">Title: {{title}}</h5>
        <p class="card-text">Details: {{details}}</p>
        <p class="card-text"><small class="text-muted">Status: {{status}}</small></p>
        <a href="#todos/edit/{{id}}" class="btn btn-primary">Edit</a>
        <button class="delete-todo btn btn-danger" data-id="{{id}}">Delete</button>
        <button class="btn btn-danger" id="hoge">hoge</button>
        <a href="#" class="btn btn-secondary">Back to List</a>
    </div>
</div>
`;

const todoEditTemplate = `
<form id="todo-edit-form" class="form">
    <div class="form-group m-3">
        <label for="todo-title">Title:</label>
        <input type="text" class="form-control" id="todo-title" placeholder="Enter Todo Title" required value="{{title}}">
    </div>
    <div class="form-group m-3">
        <label for="todo-details">Details:</label>
        <input type="text" class="form-control" id="todo-details" placeholder="Enter Details" value="{{details}}">
    </div>
    <div class="form-group m-3">
        <label for="todo-status">Status:</label>
        <select id="todo-status" class="form-control">
            <option value="未着手" {{#if (eq status '未着手')}}selected{{/if}}>未着手</option>
            <option value="進行中" {{#if (eq status '進行中')}}selected{{/if}}>進行中</option>
            <option value="完了" {{#if (eq status '完了')}}selected{{/if}}>完了</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Update Todo</button>
    <a href="#todos/{{id}}" class="btn btn-secondary">Back to Details</a>
</form>
`;