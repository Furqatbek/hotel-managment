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
  const { room } = location.state || { };
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    phone: '',
    passportNumber: '',
    checkin: '',
    checkout: '',
    paymentMethod: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const calculatePrice = () => {
    const checkinDate = dayjs(formData.checkin);
    const checkOutDate = dayjs(formData.checkout);
    const nights = checkOutDate.diff(checkinDate, 'day');

    if (nights > 0) {
      console.log("overall price: " + nights * room.price)
      return nights * room.price;
    }
    return 0
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Add form submission logic here (e.g., send data to an API)
    const totalPrice = calculatePrice()
    const bookingData = {
      customer: {
        firstName: formData.firstName,
        lastName: formData.lastName,
        phone: formData.phone,
        passNumber: formData.passportNumber
      },
      room: {
        roomNumber: '',
        price: '',
        available: false,
        capacity: '',
        level: '',
        type: ''
      },
      price: totalPrice,
      isBooked: true,
      checkin: formData.checkin,
      checkout: formData.checkout,
      paymentMethod: formData.paymentMethod
    };
    try {
      const response = await fetch("http://localhost:8080/api/v1/booking/new", {
        method: "POST",
        headers: {
          'Authorization': `Basic ${credentials}`, // Add the Authorization header
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bookingData)
      });
      if (response.ok) {
        console.log("Booking successful:", await response.json());
      } else {
        console.error("Booking failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error creating booking:", error)
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
            <MenuItem value="Cash">Наличка</MenuItem>
            <MenuItem value="Money transfer">Перевод</MenuItem>
            <MenuItem value="Bank transfer">Через банк</MenuItem>
          </Select>
        </FormControl>

        <Button type="submit" variant="contained" color="primary" fullWidth>
          Submit
        </Button>
      </form>

      {/* Back Button */}
      <Button
        variant="outlined"
        color="secondary"
        fullWidth
        sx={{ marginTop: 2 }}
        onClick={() => navigate(-1)}  // This will take the user back to the previous page
      >
        Back
      </Button>
    </Box>
  );
};

export default BookingPage;
