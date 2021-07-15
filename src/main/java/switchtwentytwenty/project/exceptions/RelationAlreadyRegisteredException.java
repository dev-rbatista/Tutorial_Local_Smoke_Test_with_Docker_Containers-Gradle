package switchtwentytwenty.project.exceptions;

public class RelationAlreadyRegisteredException extends IllegalArgumentException {
    public RelationAlreadyRegisteredException() {
        super("Relation is already registered");
    }
}