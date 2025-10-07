import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState("");

  const fetchTasks = () => {
    axios.get("http://localhost:8080/tasks").then((res) => setTasks(res.data));
  };
  useEffect(fetchTasks, []);

  const deleteTask = async (id) => {
    await axios.delete(`http://localhost:8080/tasks/${id}`);
    fetchTasks();
  };

  const addTask = async () => {
    if (!newTask.trim()) {
      alert("Task cannot be empty");
      return
    };
    await axios.post("http://localhost:8080/tasks", { title: newTask });
    setNewTask("");
    fetchTasks();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Task Manager</h1>
      <input
        type="text"
        value={newTask}
        onChange={(e) => setNewTask(e.target.value)}
        placeholder="add task"
      />
      <button onClick={addTask}>Add</button>
      <ul>
        {tasks.length == 0 ? (
          <p>no tasks</p>
        ) : (
          tasks.map((task) => (
            <li key={task.id}>
              {task.title}
              <button onClick={() => deleteTask(task.id)}>❌</button>
            </li>
          ))
        )}
      </ul>
    </div>
  );
}

export default App;
