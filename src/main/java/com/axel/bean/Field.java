package com.axel.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/2
 */
@Data
@Accessors(chain = true)
public class Field {

	private String name;
	private String value;
	private Boolean valid;
}
