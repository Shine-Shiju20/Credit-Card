import React, { useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import './App.css';

import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import Toast from './components/Toast';
import ProtectedRoute from './components/ProtectedRoute';
import FDPage from './components/FDPage';
import HeroSection from './components/HeroSection';
import Dashboard from './components/Dashboard';
import ProfilePage from './components/ProfilePage';
import AccountsPage from './components/AccountsPage';
import CreditCardsPage from './components/CreditCardsPage';
import LoansPage from './components/LoansPage';
import InvestmentsPage from './components/InvestmentsPage';
import AdminPanel from './components/AdminPanel';
import RegisterModal from './components/RegisterModal';
import LoginModal from './components/LoginModal';
import TransactionsPage from './components/TransactionsPage';
import PaymentTrackingPage from './components/PaymentTrackingPage';

const Layout = () => (
  <div className="app-layout">
    <Sidebar />
    <div className="main-content animate-fade">
      <Outlet />
    </div>
  </div>
);

function AppContent() {
  const { isLoggedIn, isLoading, toast } = useAuth();
  const [showRegisterModal, setShowRegisterModal] = useState(false);
  const [showLoginModal, setShowLoginModal] = useState(false);

  if (isLoading) {
    return (
      <div style={{
        display: 'flex', alignItems: 'center', justifyContent: 'center',
        minHeight: '100vh', background: 'var(--bg-body)'
      }}>
        <div className="loading-spinner lg" />
      </div>
    );
  }

  return (
    <div className="app">
      <Navbar
        onLoginClick={() => setShowLoginModal(true)}
        onRegisterClick={() => setShowRegisterModal(true)}
      />

      <Routes>
        {/* Public */}
        <Route
          path="/"
          element={
            isLoggedIn ? (
              <Navigate to="/dashboard" replace />
            ) : (
              <HeroSection
                onRegisterClick={() => setShowRegisterModal(true)}
                onLoginClick={() => setShowLoginModal(true)}
              />
            )
          }
        />

        {/* Protected Routes Wrapper */}
        <Route element={<ProtectedRoute><Layout /></ProtectedRoute>}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/accounts" element={<AccountsPage />} />
          <Route path="/credit-cards" element={<CreditCardsPage />} />
          <Route path="/loans" element={<LoansPage />} />
          <Route path="/fd" element={<FDPage />} />
          <Route path="/investments" element={<InvestmentsPage />} />
          <Route path="/transactions" element={<TransactionsPage />} />
          <Route path="/payment-tracking" element={<PaymentTrackingPage />} />
          <Route path="/admin" element={<AdminPanel adminOnly />} />
        </Route>

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>

      {/* Modals */}
      {showRegisterModal && (
        <RegisterModal onClose={() => setShowRegisterModal(false)} />
      )}
      {showLoginModal && (
        <LoginModal onClose={() => setShowLoginModal(false)} />
      )}

      {/* Toast */}
      {toast.show && <Toast type={toast.type} message={toast.message} />}
    </div>
  );
}

const App = () => (
  <BrowserRouter>
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  </BrowserRouter>
);

export default App;
