import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

axios.defaults.baseURL = 'http://localhost:80';

function App() {
  const [containerSize, setContainerSize] = useState('');
  const [message, setMessage] = useState('');
  const [findText, setFindText] = useState('');
  const [status, setStatus] = useState('');
  const [findResult, setFindResult] = useState('');
  const [containerCount, setContainerCount] = useState(0);
  const [recordCount, setRecordCount] = useState(0);

  useEffect(() => {
    fetchCounts();
  }, []);

  const fetchCounts = async () => {
    try {
      const containerResponse = await axios.get('/api/containers/count');
      const recordResponse = await axios.get('/api/records/count');
      setContainerCount(containerResponse.data);
      setRecordCount(recordResponse.data);
    } catch (error) {
      setStatus('Ошибка получения данных: ' + (error.response && error.response.data && error.response.data.error ? error.response.data.error : error.message));
    }
  };  

  const handleContainerSizeSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/container', { size: parseInt(containerSize) });
      setStatus('Значение контейнера установлено успешно!');
      fetchCounts();
    } catch (error) {
      setStatus('Ошибка ввода: ' + (error.response && error.response.data && error.response.data.error ? error.response.data.error : error.message));
    }
  };

  const handleMessageSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/message', { text: message });
      setStatus('Сообщение добавлено успешно!');
      fetchCounts();
    } catch (error) {
      setStatus('Ошибка ввода: ' + (error.response && error.response.data && error.response.data.error ? error.response.data.error : error.message));
    }
  };

  const handleFindSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get('/api/find', { params: { text: findText } });
      setFindResult(response.data);
    } catch (error) {
      setFindResult('Ошибка ввода: ' + (error.response && error.response.data && error.response.data.error ? error.response.data.error : error.message));
    }
  };

  return (
    <div className="App">
      <h1>Приложение для работы с базой данных</h1>
      <form onSubmit={handleContainerSizeSubmit}>
        <input
          type="number"
          value={containerSize}
          onChange={(e) => setContainerSize(e.target.value)}
          placeholder="Значение контейнера"
        />
        <button type="submit">Ввод</button>
      </form>
      <form onSubmit={handleMessageSubmit}>
        <input
          type="text"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          placeholder="Сообщение"
        />
        <button type="submit">Ввод</button>
      </form>
      <form onSubmit={handleFindSubmit}>
        <input
          type="text"
          value={findText}
          onChange={(e) => setFindText(e.target.value)}
          placeholder="Найти сообщение"
        />
        <button type="submit">Ввод</button>
      </form>
      <p>Количество контейнеров: {containerCount}</p>
      <p>Количество записей: {recordCount}</p>
      <p>{status}</p>
      <p>{findResult}</p>
    </div>
  );
}

export default App;
