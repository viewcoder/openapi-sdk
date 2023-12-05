package cn.qingque.viewcoder.openapi.sdk.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tangshiyun <tangshiyun@kuaishou.com>
 * Created on 2022-10-25
 */
@Data
@Accessors(chain = true)
public class Page<T> implements Serializable {

    private int current;

    private int size;

    private int total;

    private List<T> records;
}
