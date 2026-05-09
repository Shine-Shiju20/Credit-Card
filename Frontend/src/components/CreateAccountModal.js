import React, { useState } from 'react';
import { accountAPI } from '../api/api';
import './CreateAccountModal.css';

const CreateAccountModal = ({ onClose, onSuccess }) => {
  const [formData, setFormData] = useState({
    account_type: 'savings',
    initial_deposit: ''
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');

    try {
      const payload = {
        account_type: formData.account_type,
        initial_deposit: Number(formData.initial_deposit)
      };

      const res = await accountAPI.createAccount(payload);
      if (res.success) {
        onSuccess(res.data);
      }
    } catch (err) {
      if (err.data && err.data.errors) {
        setError(err.data.errors.join('\n'));
      } else {
        setError(err.message || 'Failed to create account');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content" style={{ maxWidth: '500px' }}>
        <div className="modal-header">
          <h2>Open New Account</h2>
          <button className="btn-close" onClick={onClose} disabled={isLoading}>
            <i className="fas fa-times"></i>
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="modal-body">
            {error && (
              <div className="alert alert-danger" style={{ whiteSpace: 'pre-line' }}>
                <i className="fas fa-exclamation-circle" />
                <span>{error}</span>
              </div>
            )}

            <div className="form-group">
              <label>Account Type</label>
              <select 
                name="account_type" 
                className="input-field"
                value={formData.account_type} 
                onChange={handleChange} 
                required
              >
                <option value="savings">Savings Account</option>
                <option value="current">Current Account</option>
                <option value="salary">Salary Account</option>
              </select>
            </div>

            <div className="form-group">
              <label>Initial Deposit</label>
              <div className="input-with-icon">
                <span className="input-icon">₹</span>
                <input
                  type="number"
                  name="initial_deposit"
                  className="input-field"
                  value={formData.initial_deposit}
                  onChange={handleChange}
                  placeholder="e.g. 5000"
                  required
                  min="1000"
                />
              </div>
              <p className="input-hint">
                Minimum ₹1,000 required to open an account
              </p>
            </div>
          </div>

          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={onClose} disabled={isLoading}>
              Cancel
            </button>
            <button type="submit" className="btn btn-primary" disabled={isLoading}>
              {isLoading ? (
                <><i className="fas fa-spinner fa-spin"></i> Processing...</>
              ) : (
                <>Open Account <i className="fas fa-plus-circle"></i></>
              )}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateAccountModal;
