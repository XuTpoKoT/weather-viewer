CREATE TABLE IF NOT EXISTS public.Locations (
    id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
    , name       text NOT NULL
    , longitude  decimal NOT NULL
    , latitude   decimal NOT NULL
);

CREATE TABLE IF NOT EXISTS public.User_Location (
    user_id       integer REFERENCES public.Users(id) ON DELETE CASCADE
    , location_id uuid REFERENCES public.Locations(id) ON DELETE CASCADE
    , CONSTRAINT user_location_constraint UNIQUE (user_id, location_id)
);
