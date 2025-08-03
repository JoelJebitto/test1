import { useEffect, useState } from 'react';
import axios from 'axios';

interface Todo {
  id: string;
  title: string;
  completed: boolean;
}

const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080/api'
});

export default function Home() {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [title, setTitle] = useState('');

  const fetchTodos = async () => {
    const res = await api.get<Todo[]>('/todos');
    setTodos(res.data);
  };

  useEffect(() => { fetchTodos(); }, []);

  const addTodo = async () => {
    if (!title) return;
    await api.post('/todos', { title });
    setTitle('');
    fetchTodos();
  };

  const toggleTodo = async (todo: Todo) => {
    await api.put(`/todos/${todo.id}`, { ...todo, completed: !todo.completed });
    fetchTodos();
  };

  const deleteTodo = async (id: string) => {
    await api.delete(`/todos/${id}`);
    fetchTodos();
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Todos</h1>
      <div>
        <input value={title} onChange={e => setTitle(e.target.value)} placeholder="New todo" />
        <button onClick={addTodo}>Add</button>
      </div>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            <input type="checkbox" checked={todo.completed} onChange={() => toggleTodo(todo)} />
            {todo.title}
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
