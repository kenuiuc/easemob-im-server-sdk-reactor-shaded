package com.easemob.im.server.api.metadata.user.get;

import com.easemob.im.server.api.AbstractApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MetadataGetTest extends AbstractApiTest {
    MetadataGet metadataGet = new MetadataGet(this.context);

    public MetadataGetTest() {
        this.server.addHandler("GET /easemob/demo/metadata/user/bob", this::handleMetadataGet);
    }

    @Test
    public void testMetadataGet() {
        assertDoesNotThrow(() -> this.metadataGet.get("bob").block(Duration.ofSeconds(3)));
    }

    public JsonNode handleMetadataGet(JsonNode req) {
        ObjectNode data = this.objectMapper.createObjectNode();
        data.put("nickname", "昵称");
        data.put("avatar", "http://www.easemob.com/avatar.png");
        data.put("phone", "159");

        ObjectNode jsonRsp = this.objectMapper.createObjectNode();
        jsonRsp.set("data", data);

        return jsonRsp;
    }
}