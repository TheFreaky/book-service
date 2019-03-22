CREATE TABLE IF NOT EXISTS public.book
(
  id           BIGSERIAL PRIMARY KEY,
  title        VARCHAR(100) NOT NULL,
  description  TEXT,
  author       VARCHAR(100),
  isbn         VARCHAR(20),
  print_year   INTEGER,
  read_already BOOLEAN,
  image_url    VARCHAR(255)
);