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
        <h3>Navigation</h3>
      </Box>
      <List>
        <ListItem button component={Link} to="/home">
          <ListItemText primary="Home" />
        </ListItem>
        <ListItem button component={Link} to="/clients">
          <ListItemText primary="Clients" />
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Sidebar;
