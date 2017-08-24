package com.comodo.parquet.service;

import com.comodo.parquet.db.CFileDAO;
import com.comodo.parquet.logger.CFileServiceLogger;
import com.comodo.parquet.model.ConnectionModel;
import org.apache.avro.generic.GenericRecord;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * can | 8/24/17.
 */
public class CFileServiceImpl implements CFileService {

    private final BlockingQueue<Path> files = new LinkedBlockingQueue<>();
    private final ExecutorService service = Executors.newCachedThreadPool();
    private final CFileServiceLogger logger = new CFileServiceLogger();
    private final CFileDAO cFileDao;

    public CFileServiceImpl(CFileDAO cFileDao) {
        this.cFileDao = cFileDao;
        service.execute(() -> {
            try {
                Path filePath = files.take();
                List<ConnectionModel> modelList = parseParquet(filePath);
                this.cFileDao.save(modelList);
            } catch (InterruptedException e) {
                logger.parquetFileProcessFailed(e);
            }
        });
    }

    private List<ConnectionModel> parseParquet(Path filePath) {
        List<ConnectionModel> connectionModels = new ArrayList<>();
        org.apache.hadoop.fs.Path path = new org.apache.hadoop.fs.Path(filePath.toString());
        try (ParquetReader<GenericRecord> reader
                 = AvroParquetReader.<GenericRecord>builder(path).build()) {
            GenericRecord genericRecord;
            while ((genericRecord = reader.read()) != null) {
                ConnectionModel connectionModel = new ConnectionModel();
                // TODO convert genericRecord values into model
                connectionModels.add(connectionModel);
            }
        } catch (Exception e) {
            logger.errorWhileConvertingToConnection(filePath, e);
        }
        return connectionModels;
    }

    public void read(Path filePath) {
        files.add(filePath);
    }
}
