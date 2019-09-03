package com.axel.test;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/3
 */
@Data
@Accessors(chain = true)
public class TestBean {

	private String id;
	private String name;
}
