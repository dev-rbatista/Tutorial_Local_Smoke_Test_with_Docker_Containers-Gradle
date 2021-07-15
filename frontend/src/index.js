import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

import reportWebVitals from './pages/reportWebVitals';
import AppProvider from "./context/AppProvider";
import App from "./App";


ReactDOM.render(
    <AppProvider>
        <React.StrictMode>

            {/*<script src="http://localhost:8097"></script>*/}
            <App />
        </React.StrictMode>
    </AppProvider>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();