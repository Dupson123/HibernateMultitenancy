CREATE TABLE schemat1.Osoba
(
  id serial NOT NULL,
  imie character varying(255) NOT NULL,
  pesel character varying(255) NOT NULL,
  data_urodzenia date,
  CONSTRAINT osoba_pkey PRIMARY KEY (id),
  CONSTRAINT uk_jb7nb34din5ajc1tujjsbx1fr UNIQUE (pesel)
)

CREATE TABLE Osoba
(
  id serial NOT NULL,
  imie character varying(255) NOT NULL,
  pesel character varying(255) NOT NULL,
  data_urodzenia date,
  CONSTRAINT osoba_pkey PRIMARY KEY (id),
  CONSTRAINT uk_jb7nb34din5ajc1tujjsbx1fr UNIQUE (pesel)
)