import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Box, Button, TextField, Typography, Select, MenuItem, InputLabel, FormControl } from '@mui/material';
import dayjs from 'dayjs';

const username = 'admin'; // Replace with your actual username
const password = 'password'; // Replace with your actual password
const credentials = btoa(`${username}:${password}`); // Base64 encode the username and password

const BookingPage = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const room = location.state || {};
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    phone: '',
    passportNumber: '',
    checkin: '',
    checkout: '',
    paymentMethod: '',
    bookPrice: '',
  });
  const handlePriceChange = (e) => {
    // Remove anything that's not a number or a decimal point
    const value = e.target.value.replace(/[^0-9.]/g, '');
    setFormData({ ...formData, bookPrice: value });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const bookingData = {
      customer: {
        firstName: formData.firstName,
        lastName: formData.lastName,
        phone: formData.phone,
        passNumber: formData.passportNumber,
      },
      room: {
        id: room.room.id,
      },
      price: formData.bookPrice,
      isBooked: true,
      checkin: formData.checkin,
      checkout: formData.checkout,
      paymentMethod: formData.paymentMethod,
    };

    try {
      const response = await fetch("http://localhost:8080/api/v1/booking/new", {
        method: "POST",
        headers: {
          'Authorization': `Basic ${credentials}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bookingData),
      });
      if (response.ok) {
        navigate(-1);
      } else {
        console.error("Booking failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error creating booking:", error);
    }
    console.log(formData);
  };

  return (
    <Box sx={{ maxWidth: 400, margin: 'auto', padding: 3, boxShadow: 3 }}>
      <Typography variant="h4" gutterBottom>
        Booking Registration
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="First Name"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Last Name"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Phone"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Passport Number"
          name="passportNumber"
          value={formData.passportNumber}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Check-in Date"
          name="checkin"
          type="datetime-local"
          value={formData.checkin}
          onChange={handleChange}
          fullWidth
          margin="normal"
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          label="Check-out Date"
          name="checkout"
          type="datetime-local"
          value={formData.checkout}
          onChange={handleChange}
          fullWidth
          margin="normal"
          InputLabelProps={{ shrink: true }}
        />
        <FormControl fullWidth margin="normal">
          <InputLabel>Payment Method</InputLabel>
          <Select
            name="paymentMethod"
            value={formData.paymentMethod}
            onChange={handleChange}
          >
            <MenuItem value="НАКД">Наличка</MenuItem>
            <MenuItem value="ПЛАСТИК">Перевод</MenuItem>
            <MenuItem value="ПЕРЕВОД">Через банк</MenuItem>
          </Select>
        </FormControl>

        <TextField
          label="Overall Price"
          value={formData.bookPrice}
          type="text"
          fullWidth
          margin="normal"
          InputLabelProps={{ shrink: true }}
          onChange={handlePriceChange}
        />

        <Button type="submit" variant="contained" color="primary" fullWidth>
          Submit
        </Button>
      </form>

      <Button
        variant="outlined"
        color="secondary"
        fullWidth
        sx={{ marginTop: 2 }}
        onClick={() => navigate(-1)}
      >
        Back
      </Button>
    </Box>
  );
};

export default BookingPage;
