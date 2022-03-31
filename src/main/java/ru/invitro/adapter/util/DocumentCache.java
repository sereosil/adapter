package ru.invitro.adapter.util;

import com.fasterxml.uuid.impl.UUIDUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import ru.invitro.adapter.model.exception.DocumentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DocumentCache {

    private static final long STEP_MILLIS = 50L;

    private static final int MAX_STEPS = 5;

    private final Cache cache;

    @Autowired
    public DocumentCache(RedisCacheManager documentCacheManager) {
        this.cache = documentCacheManager.getCache("documents");
    }

    public byte[] get(UUID id) throws DocumentNotFoundException {
        byte[] b;
        int count = 0;

        do {
            b = (byte[]) Optional.ofNullable(cache.get(UUIDUtil.asByteArray(id))).map(Cache.ValueWrapper::get).orElse(null);
        } while (b == null && sleep(count++));
        if (b == null) {
            throw new DocumentNotFoundException("No document found in cache!");
        }
        return b;
    }

    public List<byte[]> getByteList(String[] id) throws DocumentNotFoundException {
        byte[] b;
        List<byte[]> fileBytes = new ArrayList<>();
        int count = 0;
        for (String ids : id) {
            do {
                b = get(UUID.fromString(ids));
            } while (b == null && sleep(count++));
            fileBytes.add(b);
        }
        return fileBytes;
    }

    @SneakyThrows
    private boolean sleep(int count) {
        if (count <= MAX_STEPS) {
            Thread.sleep((1L << count) * STEP_MILLIS);
            return true;
        }
        return false;
    }
}
