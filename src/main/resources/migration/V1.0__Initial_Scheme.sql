CREATE TABLE IF NOT EXISTS public.Users (
    id         serial PRIMARY KEY
    , login    text NOT NULL UNIQUE
    , password text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.Sessions (
    id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
    , user_id    integer   NOT NULL REFERENCES public.Users(id) ON DELETE CASCADE
    , expires_at timestamp NOT NULL
);
