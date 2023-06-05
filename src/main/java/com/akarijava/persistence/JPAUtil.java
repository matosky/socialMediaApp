package com.akarijava.persistence;
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.Persistence;

    public class JPAUtil {
        private static final String PERSISTENCE_UNIT_NAME = "social-media-persistence-unit";
        private static final EntityManagerFactory entityManagerFactory;

        static {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static EntityManager getEntityManager() {
            return entityManagerFactory.createEntityManager();
        }

        public static void closeEntityManagerFactory() {
            entityManagerFactory.close();
        }
    }

