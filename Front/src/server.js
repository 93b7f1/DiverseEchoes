const express = require('express');
const path = require('path');

const app = express();
const port = 3000;

const folderPath = 'C:\\Users\\call1\\Desktop\\DiverseEchoes\\Archives';

app.use(express.static(folderPath));

app.listen(port, () => {
  console.log(`Server listening on http://localhost:${port}`);
});