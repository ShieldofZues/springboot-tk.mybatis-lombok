package com.example.demo.common;


import org.apache.commons.beanutils.PropertyUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * BaseEntity
 */
public abstract class BaseEntity implements Serializable {

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		Arrays.stream(PropertyUtils.getPropertyDescriptors(this))
				.filter((p) -> PropertyUtils.isReadable(this, p.getName())
						&& PropertyUtils.isWriteable(this, p.getName()))
				.forEach((p) -> {
					stringBuffer.append(p.getName() + ": ");
					try {
						Object value = PropertyUtils.getProperty(this, p.getName());
						stringBuffer.append(value);
					} catch (Exception e) {
						stringBuffer.append("");
					}
					stringBuffer.append(",");
                    //strToReturn.append(System.getProperty("line.separator"));
				});
		stringBuffer.insert(0, "{").insert(stringBuffer.length(), "}");
		return stringBuffer.toString();
	}
}