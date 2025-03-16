import React from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Admin from './pages/Admin';
import Bmo from './pages/Bmo';
import Dho from './pages/Dho';
import NotFound from './pages/NotFound';
import RegisterUser from './pages/UserRegistrationForm'

const getRole = () => {
  return localStorage.getItem('role');
};

const getToken = () => {
  return localStorage.getItem('token');
};

const router = createBrowserRouter([
  {
    path: '/',
    element: getToken() ? (
      getRole() === 'ADMIN' ? <Navigate to="/admin" /> :
      getRole() === 'BMO' ? <Navigate to="/bmo" /> :
      getRole() === 'DHO' ? <Navigate to="/dho" /> :
      <Navigate to="/login" />
    ) : <Navigate to="/login" />
  },
  {
    path: '/login',
    element: <Login />,
  },
  {
    path: '/admin',
    element: getRole() === 'ADMIN' ? <Admin /> : <Navigate to="/login" />,
  },
  {
    path: '/bmo',
    element: getRole() === 'BMO' ? <Bmo /> : <Navigate to="/login" />,
  },
  {
    path: '/dho',
    element: getRole() === 'DHO' ? <Dho /> : <Navigate to="/login" />,
  },
  {
    path: '/register',
    element: <RegisterUser />
  },
  {
    path: '*',
    element: <NotFound />,
  }
]);

createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)

