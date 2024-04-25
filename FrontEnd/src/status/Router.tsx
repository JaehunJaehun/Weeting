import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import MainLogin from '../components/main/MainLogin';
import MainGuest from '../components/main/MainGuest';
import MainSignup from '../components/main/MainSignup';
import MainPage from '@/pages/MainPage';
import GameWaiting from '../components/game/GameWaiting';
import GamePage from '@/pages/GamePage';
import HomePage from '@/pages/HomePage';
import ErrorPage from '@/pages/ErrorPage';
import PrivateRoute from '@/status/PrivateRoute';
import { getCookie } from '@/services/axios';

const Router = () => {
  const token: string | undefined = getCookie('accessToken');

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />}>
          <Route index element={<MainGuest />} />
          <Route path="login" element={<MainLogin />} />
          <Route path="signup" element={<MainSignup />} />
        </Route>
        <Route element={<PrivateRoute token={token} />}>
          <Route path="/game" element={<GamePage />}>
            <Route index element={<GameWaiting />} />
          </Route>
          <Route path="/room" />
          <Route path="/custom" />
          <Route path="/ranking" />
          <Route path="/home" element={<HomePage />} />
        </Route>
        <Route path="/error" element={<ErrorPage />} />
        <Route path="*" element={<Navigate replace to="/error" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
