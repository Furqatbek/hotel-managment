import React, { useEffect, useState } from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  Box,
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  CircularProgress,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import CleaningServicesIcon from '@mui/icons-material/CleaningServices';
import BookIcon from '@mui/icons-material/Book';
import VisibilityIcon from '@mui/icons-material/Visibility';
import OrderDetailsModal from "./OrderDetailsModal";

const HomePage = () => {
  const [rooms, setRooms] = useState([]);
  const [filteredRooms, setFilteredRooms] = useState([]);
  const [availabilityFilter, setAvailabilityFilter] = useState('');
  const [cleaningFilter, setCleaningFilter] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const username = 'admin';
  const password = 'password';
  const credentials = btoa(`${username}:${password}`);
  const [isModalOpen, setModalOpen] = useState(false);
  const [orderDetails, setOrderDetails] = useState(null);

  const handleOpenModal = async (roomId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/v1/booking/search/by-room-id/${roomId}`, {
        method: 'GET',
        headers: {
          'Authorization': `Basic ${credentials}`,
          'Content-Type': 'application/json',
        },});
      if (!response.ok) {
        throw new Error('Network response was not ok')
      }

      const data = await response.json();
      setOrderDetails(data);
      setModalOpen(true);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setOrderDetails(null);
  }


  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/v1/rooms/all', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${credentials}`,
            'Content-Type': 'application/json',
          },
        });

        if (response.ok) {
          const data = await response.json();
          setRooms(data);
          setFilteredRooms(data); // Set filteredRooms to the fetched data
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

  // Apply filters when filter values change
  useEffect(() => {
    handleFilterChange();
  }, [availabilityFilter, cleaningFilter]);

  const handleFilterChange = () => {
    let filteredData = rooms;

    if (availabilityFilter) {
      filteredData = filteredData.filter(room => room.available === (availabilityFilter === 'available'));
    }

    if (cleaningFilter) {
      filteredData = filteredData.filter(room => room.cleaning === (cleaningFilter === 'cleaning'));
    }

    setFilteredRooms(filteredData); // Update filteredRooms instead of rooms
  };

  const handleNewOrder = (room) => {
    navigate('/booking', { state: { room } }); // Pass the specific room instead of all rooms
  };

  const handleCleaningStatusChange = async (roomNumber, startCleaning) => {
    try {
      const room = rooms.find((room) => room.roomNumber === roomNumber);
      if (!room) {
        setError(`Room ${roomNumber} not found.`);
        return;
      }

      const response = await fetch(`http://localhost:8080/api/v1/rooms/update/${roomNumber}`, {
        method: 'POST',
        headers: {
          Authorization: `Basic ${credentials}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...room,
          cleaning: startCleaning,
        }),
      });

      if (response.ok) {
        // Ensure state is updated with the new cleaning status
        setRooms((prevRooms) =>
          prevRooms.map((room) =>
            room.roomNumber === roomNumber ? { ...room, cleaning: startCleaning } : room
          )
        );
        setFilteredRooms((prevFilteredRooms) =>
          prevFilteredRooms.map((room) =>
            room.roomNumber === roomNumber ? { ...room, cleaning: startCleaning } : room
          )
        );
      } else {
        setError('Error updating cleaning status');
      }
    } catch (error) {
      setError('Error updating cleaning status');
    }
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
        <Typography variant="h6" color="error">
          {error}
        </Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ padding: 2 }}>
      <Box sx={{ mb: 3, padding: 2, backgroundColor: '#f0f0f0', borderRadius: 2, boxShadow: 2 }}>
        <Grid container spacing={2} justifyContent="space-between">
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

      <Grid container spacing={2} justifyContent="center">
        {filteredRooms.map((room) => (
          <Grid item xs={12} sm={6} md={3} key={room.id}>
            <Card
              sx={{
                width: '100%',
                boxShadow: 3,
                backgroundColor: room.cleaning ? '#FFF3CD' : room.available ? '#DFF0D8' : '#F8D7DA',
                border: room.cleaning
                  ? '2px solid #FFC107'
                  : room.available
                    ? '2px solid #28A745'
                    : '2px solid #D32F2F',
                transition: 'background-color 0.3s ease, border 0.3s ease',
              }}
            >
              <CardContent sx={{ padding: 1 }}>
                <Typography variant="h6" gutterBottom>
                  Комната {room.roomNumber}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Тип комната: {room.roomType}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Нужно уборка: {room.cleaning ? 'Да' : 'Нет'}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Доступность: {room.available ? 'Доступно' : 'Не доступно'}
                </Typography>
              </CardContent>
              <Box sx={{ padding: 1 }}>
                <Button
                  variant="contained"
                  color="primary"
                  fullWidth
                  startIcon={<BookIcon />}
                  disabled={!room.available}
                  onClick={() => handleNewOrder(room)}
                >
                  Бронировать
                </Button>
                <Box sx={{ marginTop: 1 }}>
                  {
                    room.cleaning && (
                  <Button
                    variant="outlined"
                    color="secondary"
                    fullWidth
                    startIcon={<CleaningServicesIcon />}
                    onClick={() => handleCleaningStatusChange(room.roomNumber, !room.cleaning)}
                  >
                    {room.cleaning ? 'Сделать уборку' : 'Завершить уборку'}
                  </Button>
                    )
                  }
                </Box>
                <Box sx={{ marginTop: 1 }}>
                  <Button
                    variant="outlined"
                    color="info"
                    fullWidth
                    disabled={room.available}
                    startIcon={<VisibilityIcon />}
                    onClick={() => handleOpenModal(room.id)} // Update button action
                  >
                    Подробнее о заказе
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
