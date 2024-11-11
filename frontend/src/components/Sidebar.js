import React from 'react';
import { Link } from 'react-router-dom';
import { List, ListItem, ListItemText, Drawer, Box } from '@mui/material';

const Sidebar = () => {
  return (
    <Drawer
      variant="permanent"
      anchor="left"
      sx={{
        width: 240,
        '& .MuiDrawer-paper': {
          width: 240,
          boxSizing: 'border-box',
          backgroundColor: '#f4f4f4',
        },
      }}
    >
      <Box sx={{ padding: 2 }}>
        <h3>Emirates Plaza</h3>
      </Box>
      <List>
        <ListItem button component={Link} to="/home">
          <ListItemText primary="Комнаты" />
        </ListItem>
        <ListItem button component={Link} to="/clients">
          <ListItemText primary="Клиенты" />
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Sidebar;
