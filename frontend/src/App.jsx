import { useEffect, useState } from "react";
import axios from "axios";
import toast, { Toaster } from "react-hot-toast";

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState("");
  const [loading, setLoading] = useState(false);

  function taskAddNotification() {
    toast.success("task added successfully");
  }
  function taskDeleteNotification() {
    toast.success("task deleted successfully");
  }
  function errorNotification() {
    toast.error("something went wrong");
  }

  useEffect(() => {
    const fetchTasks = async () => {
      setLoading(true);
      try {
        const res = await axios.get("http://localhost:8080/tasks");
        setTasks(res.data);
      } catch (error) {
        console.error("Error fetching tasks:", error.message);
        errorNotification();
      } finally {
        setLoading(false);
      }
    };

    fetchTasks();
  }, []);

  const deleteTask = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/tasks/${id}`);
      setTasks((prev) => prev.filter((task) => task.id !== id));
      taskDeleteNotification();
    } catch (error) {
      console.error("Error deleting task:", error.message);
      errorNotification();
      return;
    }
  };

  const addTask = async () => {
    if (!newTask.trim()) {
      errorNotification()
      return;
    }
    try {
      const res = await axios.post("http://localhost:8080/tasks", {
        title: newTask,
      });
      setTasks((prev) => [...prev, res.data]); // update local state
      setNewTask("");

      taskAddNotification();
    } catch (error) {
      console.error("Error adding task:", error.message);
      errorNotification();
      
      return;
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <Toaster />
      <h1>Task Manager</h1>
      <input
        type="text"
        value={newTask}
        onChange={(e) => setNewTask(e.target.value)}
        placeholder="add task"
        onKeyDown={(e) => {
          if (e.key === "Enter") addTask();
        }}
      />
      <button onClick={addTask}>Add</button>
      <ul>
        {loading ? (
          <p>Loading...</p>
        ) : tasks.length === 0 ? (
          <p>No tasks</p>
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
