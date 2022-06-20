package com.aliyun.gts.bigdata.blink.udx;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.ScalarFunction;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ParseAvroUdf extends ScalarFunction {
    private Schema schema = null;
    private DatumReader<GenericRecord> datumReader = null;

    @Override
    public void open(FunctionContext context)  {

    }

    /**
     *
     * @param strByteArray  mq 消费到 avro byte array 数据
     * @param schemaFilePath avro 定义文件路径，通过blink资源引入方式，该参数直接传入文件的绝对路径，如/user.avsc
     * @return
     * @throws IOException
     */
    public String eval(byte[] strByteArray, String schemaFilePath) throws IOException {
        // "/user.avsc"
        if (schema == null) {
            schema = new Schema.Parser().parse(ParseAvroUdf.class.getResourceAsStream(schemaFilePath));
            datumReader = new GenericDatumReader<GenericRecord>(schema);
        }

        BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(strByteArray), null);
        GenericRecord recordRaw = datumReader.read(null, binaryDecoder);

        return recordRaw.toString();
    }

    //可选，close方法可以不写
    @Override
    public void close() {

    }
}
