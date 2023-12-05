package cn.qingque.viewcoder.openapi.sdk.model;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-01
 */
public class Pair <F, S> {
    public F first;
    public S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}
