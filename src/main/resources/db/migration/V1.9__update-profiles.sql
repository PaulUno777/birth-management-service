

ALTER TABLE IF EXISTS public.profiles ADD COLUMN is_active boolean DEFAULT false;

ALTER TABLE IF EXISTS public.profiles ADD COLUMN phone_verified boolean DEFAULT false;

ALTER TABLE IF EXISTS public.profiles ADD COLUMN role_id INTEGER;

ALTER TABLE IF EXISTS public.profiles ADD CONSTRAINT fk_profiles_roles FOREIGN KEY (role_id) REFERENCES public.roles(id)
MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

