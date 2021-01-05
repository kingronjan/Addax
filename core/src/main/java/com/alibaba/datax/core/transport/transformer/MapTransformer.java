package com.alibaba.datax.core.transport.transformer;

import com.alibaba.datax.common.element.Column;
import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.common.element.StringColumn;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.util.MathUtil;
import com.alibaba.datax.transformer.Transformer;

import java.util.Arrays;

/**
 * no comments.
 * Created by liqiang on 16/3/4.
 */
public class MapTransformer
        extends Transformer
{
    public MapTransformer()
    {
        setTransformerName("dx_map");
    }

    @Override
    public Record evaluate(Record record, Object... paras)
    {

        int columnIndex;
        String code;
        String value;
        String newValue;
        Column column;
        int scale = 2; //默认精度

        try {
            if (paras.length != 3) {
                throw new RuntimeException("dx_map paras must be 3");
            }

            columnIndex = (Integer) paras[0];
            code = (String) paras[1];
            value = (String) paras[2];
            column = record.getColumn(columnIndex);
            if (column.getRawData() == null) {
                return record;
            }

            Double.valueOf(column.asString());
            Double.valueOf(value);
        }
        catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_ILLEGAL_PARAMETER, "paras:" + Arrays.asList(paras).toString() + " => " + e.getMessage());
        }

        if (column.asString().split("\\.").length >= 2) {
            scale = column.asString().split("\\.")[1].length();
        }

        try {
            switch (code) {
                case "+":
                    newValue = MathUtil.add(column.asString(), value);
                    break;
                case "-":
                    newValue = MathUtil.subtract(column.asString(), value);
                    break;
                case "*":
                    newValue = MathUtil.multiply(column.asString(), value);
                    break;
                case "/":
                    newValue = MathUtil.divide(column.asString(), value, scale);
                    break;
                case "%":
                    newValue = MathUtil.mod(column.asString(), value);
                    break;
                case "^":
                    newValue = MathUtil.pow(column.asString(), value);
                    break;
                default:
                    throw new RuntimeException("dx_map can't support code:" + code);
            }
            record.setColumn(columnIndex, new StringColumn(newValue));
            return record;
        }
        catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_RUN_EXCEPTION, e.getMessage(), e);
        }
    }
}