package com.app.beans;
import com.beans.Root;
public class RootResponse {
private Root root;
public RootResponse() {
	// TODO Auto-generated constructor stub
}
public RootResponse(Root obj) {
	super();
	this.root = obj;
}

public void setRoot(Root root) {
	this.root = root;
}
public Root getRoot() {
	return root;
}
@Override
public String toString() {
	return "Response [root=" + root + "]";
}

}
