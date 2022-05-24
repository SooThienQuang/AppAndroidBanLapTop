package com.example.shopmoon.Model;

import java.io.Serializable;

public class ProducerType implements Serializable {
    public String proProducer,proProducerName,proProducerPhoto;

    public ProducerType(String proProducer, String proProducerName, String proProducerPhoto) {
        this.proProducer = proProducer;
        this.proProducerName = proProducerName;
        this.proProducerPhoto = proProducerPhoto;
    }

    public String getProProducer() {
        return proProducer;
    }

    public void setProProducer(String proProducer) {
        this.proProducer = proProducer;
    }

    public String getProProducerName() {
        return proProducerName;
    }

    public void setProProducerName(String proProducerName) {
        this.proProducerName = proProducerName;
    }

    public String getProProducerPhoto() {
        return proProducerPhoto;
    }

    public void setProProducerPhoto(String proProducerPhoto) {
        this.proProducerPhoto = proProducerPhoto;
    }
}
