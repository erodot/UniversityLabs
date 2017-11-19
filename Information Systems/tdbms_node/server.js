const app = require('./scripts/app');
const config = require('./config')

// Outer connection establishing
app.listen(config.server.port, config.server.hostname, function(){
  console.log(`Server running at http://${config.server.hostname}:${config.server.port}/`);
});