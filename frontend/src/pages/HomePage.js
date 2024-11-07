import React, { useEffect, useState } from 'react';
import { Grid, Card, CardContent, Typography, Box, Button, FormControl, InputLabel, Select, MenuItem, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
  const [rooms, setRooms] = useState([]);
  const [filteredRooms, setFilteredRooms] = useState([]);
  const [availabilityFilter, setAvailabilityFilter] = useState('');
  const [cleaningFilter, setCleaningFilter] = useState('');
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state
  const navigate = useNavigate();
  const username = 'admin'; // Replace with your actual username
  const password = 'password'; // Replace with your actual password
  const credentials = btoa(`${username}:${password}`); // Base64 encode the username and password

  useEffect(() => {
    const fetchRooms = async () => {

      try {
        const response = await fetch('http://localhost:8080/api/v1/rooms/all', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${credentials}`, // Add the Authorization header
            'Content-Type': 'application/json',
          },
        });

        if (response.ok) {
          const data = await response.json();
          setRooms(data);
          setLoading(false);
        } else {
          setError('Error fetching rooms');
          setLoading(false);
        }
      } catch (error) {
        setError('Error fetching rooms');
        setLoading(false);
      }
    };

    fetchRooms();
  }, []);

  // Filter rooms based on selected filters
  const handleFilterChange = () => {
    let filteredData = rooms;

    if (availabilityFilter) {
      filteredData = filteredData.filter(room => room.available === (availabilityFilter === 'available'));
    }

    if (cleaningFilter) {
      filteredData = filteredData.filter(room => room.cleaning === (cleaningFilter === 'cleaning'));
    }

    setFilteredRooms(filteredData);
  };

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
        <Typography variant="h6" color="error">{error}</Typography>
      </Box>
    );
  }

  // Handler functions for the new buttons
  const handleStartCleaning = async (roomNumber) => {
    console.log(`Starting cleaning for room ${roomNumber}`);
    try {
      const room = rooms.find(room => room.roomNumber === roomNumber);
      const response = await fetch(`http://localhost:8080/api/v1/rooms/update/${roomNumber}`, {
      method: 'POST',
      headers: {
        'Authorization': `Basic ${credentials}`, // Add the Authorization header
        'Content-Type': 'application/json',
    },
      body: JSON.stringify({
        ...room,
        cleaning:true }),

    });

    if (response.ok) {
      setRooms((prevRooms) =>
      prevRooms.map((room) => room.roomNumber === roomNumber ? { ...room, cleaning: true} : room));
    } else  {
    setError('Error updating cleaning status');
    }
    } catch (error) {
      setError('Error updating cleaning status')
    }
    }

  const handleFinishCleaning = async (roomNumber) => {
    console.log(`Finish cleaning for room ${roomNumber}`);
    try {
      const room = rooms.find(room => room.roomNumber === roomNumber);
      const response = await fetch(`http://localhost:8080/api/v1/rooms/update/${roomNumber}`, {
        method: 'POST',
        headers: {
          'Authorization': `Basic ${credentials}`, // Add the Authorization header
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...room,
          cleaning:false }),

      });

      if (response.ok) {
        setRooms((prevRooms) =>
          prevRooms.map((room) => room.roomNumber === roomNumber ? { ...room, cleaning: false} : room));
      } else  {
        setError('Error updating cleaning status');
      }
    } catch (error) {
      setError('Error updating cleaning status')
    }
  }

  const handleCloseRoom = (roomId) => {
    console.log(`Closing room ${roomId}`);
    // Add logic to close the room (e.g., API call)
  };

  return (
    <Box sx={{ padding: 3 }}>
      {/* Filter Zone */}
      <Box sx={{ mb: 3, padding: 2, backgroundColor: '#f0f0f0', borderRadius: 2, boxShadow: 2 }}>
        <Grid container spacing={3} justifyContent="space-between">
          <Grid item xs={12} sm={6} md={3}>
            <FormControl fullWidth>
              <InputLabel>Availability</InputLabel>
              <Select
                value={availabilityFilter}
                label="Availability"
                onChange={(e) => setAvailabilityFilter(e.target.value)}
                fullWidth
              >
                <MenuItem value="">All</MenuItem>
                <MenuItem value="available">Available</MenuItem>
                <MenuItem value="unavailable">Not Available</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <FormControl fullWidth>
              <InputLabel>Cleaning Status</InputLabel>
              <Select
                value={cleaningFilter}
                label="Cleaning Status"
                onChange={(e) => setCleaningFilter(e.target.value)}
                fullWidth
              >
                <MenuItem value="">All</MenuItem>
                <MenuItem value="cleaning">Cleaning</MenuItem>
                <MenuItem value="notCleaning">Not Cleaning</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={12} sm={6} md={3} alignSelf="center">
            <Button variant="contained" color="primary" onClick={handleFilterChange} fullWidth>
              Apply Filters
            </Button>
          </Grid>
        </Grid>
      </Box>

      <Grid container spacing={3} justifyContent="center">
        {rooms.map((room) => (
          <Grid item xs={12} sm={6} md={4} key={room.id}>
            <Card sx={{
              maxWidth: 345,
              boxShadow: 3,
              backgroundColor: room.cleaning
                ? '#FFF3CD' // Light yellow if cleaning
                : room.available
                  ? 'white' // White if available
                  : '#F8D7DA', // Light red if not available
              border: room.cleaning
                ? '2px solid #FFC107' // Yellow border if cleaning
                : room.available
                  ? 'none' // No border if available
                  : '2px solid #D32F2F', // Red border if not available
              transition: 'background-color 0.3s ease, border 0.3s ease', // Smooth transition
            }}>
              <CardContent>
                <Typography variant="h5" component="div" gutterBottom>
                  Комната {room.roomNumber}
                </Typography>
                <Typography variant="body1" color="text.secondary">
                  Цена: {room.price} сум
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Тип комната: {room.roomType}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Количество кроватов: {room.capacity} person(s)
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Этаж: {room.level}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Идет уборка: {room.cleaning ? 'Да' : 'Нет'}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Доступность: {room.available ? 'Доступно' : 'Не доступно'}
                </Typography>
              </CardContent>
              <Box sx={{ padding: 2 }}>
                <Button
                  variant="contained"
                  color="primary"
                  fullWidth
                  disabled={!room.available} // Disabled if room is not available
                  onClick={() => navigate('/booking')}
                >
                  Бронировать
                </Button>
                <Box sx={{ marginTop: 2 }}>
                  <Button
                    variant="outlined"
                    color="secondary"
                    fullWidth
                    onClick={() => room.cleaning ? handleFinishCleaning(room.roomNumber) : handleStartCleaning(room.roomNumber)}
                  >
                    {room.cleaning ? 'Завершить уборку' : 'Начать уборку'}
                  </Button>
                </Box>
                <Box sx={{ marginTop: 2 }}>
                  <Button
                    variant="outlined"
                    color="error"
                    fullWidth
                    onClick={() => handleCloseRoom(room.id)}
                  >
                    Закрыть комнату
                  </Button>
                </Box>
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};
export default HomePage;
