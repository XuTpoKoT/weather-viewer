CREATE TABLE IF NOT EXISTS public.Locations (
    id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
    , name       text NOT NULL
    , longitude  decimal NOT NULL
    , latitude   decimal NOT NULL
    , CONSTRAINT location_constraint UNIQUE (longitude, latitude)
);

CREATE TABLE IF NOT EXISTS public.User_Location (
    id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
    , user_id    integer REFERENCES public.Users(id) ON DELETE CASCADE
    , longitude  decimal NOT NULL
    , latitude   decimal NOT NULL
    , CONSTRAINT user_location_constraint UNIQUE (user_id, longitude, latitude)
);
