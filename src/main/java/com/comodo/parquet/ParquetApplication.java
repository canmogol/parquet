package com.comodo.parquet;

import com.comodo.parquet.db.*;
import com.comodo.parquet.logger.ParquetApplicationLogger;
import com.comodo.parquet.model.ConnectionModel;
import com.comodo.parquet.resources.CConnectionResource;
import com.comodo.parquet.resources.CFileResource;
import com.comodo.parquet.resources.QConnectionResource;
import com.comodo.parquet.resources.SpaRouterResource;
import com.comodo.parquet.service.*;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class ParquetApplication extends Application<ParquetConfiguration> {

    private static final String CONFIGURATION_FILE = "config.yml";
    public static final String SERVER = "server";

    public static final ParquetApplicationLogger logger = new ParquetApplicationLogger();
    public static final String SPA = "/spa/";


    /**
     * Query Stack's connection
     */
    private final HibernateBundle<ParquetConfiguration> hibernateQueryBundle =
        new HibernateBundle<ParquetConfiguration>(ConnectionModel.class) {
            @Override
            protected String name() {
                return "QUERY-" + super.name();
            }

            @Override
            public DataSourceFactory getDataSourceFactory(ParquetConfiguration parquetConfiguration) {
                return parquetConfiguration.getQueryDataSourceFactory();
            }
        };

    /**
     * Command Stack's connection
     */
    private final HibernateBundle<ParquetConfiguration> hibernateCommandBundle =
        new HibernateBundle<ParquetConfiguration>(ConnectionModel.class) {

            @Override
            protected String name() {
                return "COMMAND-" + super.name();
            }

            @Override
            public DataSourceFactory getDataSourceFactory(ParquetConfiguration parquetConfiguration) {
                return parquetConfiguration.getCommandDataSourceFactory();
            }
        };


    private final SwaggerBundle<ParquetConfiguration> swaggerBundle =
        new SwaggerBundle<ParquetConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ParquetConfiguration parquetConfiguration) {
                return parquetConfiguration.getSwaggerBundleConfiguration();
            }
        };


    public static void main(final String[] args) throws Exception {
        final String[] arguments;
        if (args.length != 0) {
            arguments = args;
        } else {
            arguments = new String[]{SERVER, CONFIGURATION_FILE};
            logger.usingInternalConfiguration(arguments);
        }
        new ParquetApplication().run(arguments);
    }

    @Override
    public String getName() {
        return "Parquet";
    }

    @Override
    public void initialize(final Bootstrap<ParquetConfiguration> bootstrap) {
        // map root request to the contents of the assets folder,
        // using index.html as index file since the client is an SPA
        bootstrap.addBundle(new AssetsBundle("/assets/", SPA, "index.html"));
        bootstrap.addBundle(hibernateCommandBundle);
        bootstrap.addBundle(hibernateQueryBundle);
        bootstrap.addBundle(swaggerBundle);
    }

    @Override
    public void run(final ParquetConfiguration configuration, final Environment environment) {

        // register spa router resource
        environment.jersey().register(new SpaRouterResource(SPA));


        // COMMAND STACK
        // Connection
        CConnectionDAO connectionCommandDAO = new CConnectionDAOHibernate(hibernateCommandBundle.getSessionFactory());
        CConnectionService connectionCommandService = new CConnectionServiceImpl(connectionCommandDAO);
        CConnectionResource connectionCommandResource = new CConnectionResource(connectionCommandService);
        environment.jersey().register(connectionCommandResource);
        // File
        CFileDAO fileCommandDAO = new CFileDAOHibernate();
        CFileServiceImpl fileCommandService = new CFileServiceImpl(fileCommandDAO);
        CFileResource fileCommandResource = new CFileResource(fileCommandService);
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(fileCommandResource);


        // QUERY STACK
        QConnectionDAO connectionQueryDAO = new QConnectionDAOHibernate(hibernateQueryBundle.getSessionFactory());
        QConnectionService connectionQueryService = new QConnectionServiceImpl(connectionQueryDAO);
        QConnectionResource connectionQueryResource = new QConnectionResource(connectionQueryService);
        environment.jersey().register(connectionQueryResource);

    }


}
