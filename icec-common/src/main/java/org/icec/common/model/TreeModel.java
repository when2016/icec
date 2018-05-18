package org.icec.common.model;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeModel {
	public abstract Integer getId();

	public abstract Integer getParentId();

	public abstract String getName();

	/**
	 * 子节点的集合
	 */
	protected List<TreeModel> children=new ArrayList<TreeModel>();

	public List<TreeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}
}
