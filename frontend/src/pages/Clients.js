import React, { useEffect, useState } from 'react';
import { List, ListItem, ListItemText, ListItemAvatar, Avatar, Typography, Box, Button, Divider, TextField, Grid } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';

const Clients = () => {
  const [clients, setClients] = useState([]);
  const [filteredClients, setFilteredClients] = useState([]);
  const [phoneFilter, setPhoneFilter] = useState('');
  const [passNumFilter, setPassNumFilter] = useState('');
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state
  const username = 'admin'; // Replace with your actual username
  const password = 'password'; // Replace with your actual password
  const credentials = btoa(`${username}:${password}`); // Base64 encode the username and password

  useEffect(() => {
    const fetchClients = async () => {

      try {
        const response = await fetch('http://localhost:8080/api/v1/customers/all', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${credentials}`, // Add the Authorization header
            'Content-Type': 'application/json',
          },
        });

        if (response.ok) {
          const data = await response.json();
          setClients(data);
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

    fetchClients();
  }, []);

  useEffect(() => {
    // Filter clients whenever phoneFilter or passNumFilter changes
    setFilteredClients(
      clients.filter(
        (client) =>
          client.phone.includes(phoneFilter) &&
          (client.passNumber ? client.passNumber.includes(passNumFilter) : true)
      )
    );
  }, [phoneFilter, passNumFilter, clients]);

  return (
    <Box sx={{ padding: 3 }}>

      {/* Filter Zone */}
      <Box sx={{ marginBottom: 3 }}>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6} md={4}>
            <TextField
              fullWidth
              label="Search by Phone"
              variant="outlined"
              value={phoneFilter}
              onChange={(e) => setPhoneFilter(e.target.value)}
              placeholder="Enter phone number"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <TextField
              fullWidth
              label="Search by Passport Number"
              variant="outlined"
              value={passNumFilter}
              onChange={(e) => setPassNumFilter(e.target.value)}
              placeholder="Enter passport number"
            />
          </Grid>
        </Grid>
      </Box>

      {/* Clients List */}
      <List>
        {filteredClients.map((client) => (
          <Box key={client.id} sx={{ boxShadow: 1, borderRadius: 1, mb: 2, bgcolor: 'background.paper' }}>
            <ListItem>
              <ListItemAvatar>
                <Avatar>
                  <PersonIcon />
                </Avatar>
              </ListItemAvatar>
              <ListItemText
                primary={`${client.firstName} ${client.lastName}`}
                secondary={
                  <>
                    <Typography variant="body2" color="text.secondary">
                      Phone: {client.phone}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      Passport Number: {client.passNumber || 'Not Provided'}
                    </Typography>
                  </>
                }
              />
              <Button variant="contained" color="primary" sx={{ ml: 2 }}>
                View Profile
              </Button>
            </ListItem>
            <Divider />
          </Box>
        ))}
      </List>
    </Box>
  );
};

export default Clients;
