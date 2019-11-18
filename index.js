const express = require('express');
const axios = require('axios')
const app = express();

const inventory_host = process.env.INVENTORY_HOST || 'localhost';
const inventory_port = process.env.INVENTORY_PORT || '8080';

const items = [
    {
        id: 1,
        categoryId: 100,
        description: "item 1"
    },
    {
        id: 2,
        categoryId: 200,
        description: "item 2"
    },
    {
        id: 3,
        categoryId: 100,
        description: "item 3"
    }
]


app.get('/catalog/:categoryId?', async (req, res) => {
    var categoryId = req.params.categoryId;
    var data = items.filter(item => categoryId != null ? item.categoryId==categoryId : true);
    
    for(i=0; i<data.length; i++){
        element = data[i];
        var quantity = null;
        try{
            var inventory = await axios.get('http://'+inventory_host+':'+inventory_port+'/inventory/'+element.id)
            quantity = inventory.data.quantity
        }catch(e){
            console.error(e)
        }
        element['quantity'] = quantity
    }
    
    res.json(data)
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log('Catalog Service listening on port', port);
});