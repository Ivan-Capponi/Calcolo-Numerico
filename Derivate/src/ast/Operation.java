package ast;

import derivation.Visitor;

public interface Operation {
	<T> T accept(Visitor <T> v);
	Double getNumericResult(Double val);
}
