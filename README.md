## NAME OF PROJECT
Kenya Wildlife Tracker

## Description
The wildlife tracker application helps to keep track of wild animals in the forest without loosing count,The application allows the user to add the details ot the animal if they are spotted regulary or they
are of the endangered species.The application has the Sightings section where the userenters the type of animal spotted and also the time it was spotted and also the location in order to help
in keeping track of the animal.

### By David Mochoge

## Setup/Installation Requirements
<ul>
<li>Download/clone Repo.</li>
<li>open with your text editor of choice.</li>
<li>Google chrome or any browser.</li>


#### To re-create the database, follow these steps;
In PSQL:
* CREATE DATABASE wildlife_tracker;
* \c wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar);
* CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);
* CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

