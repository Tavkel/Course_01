import java.util.EnumSet;

public enum FieldFlags {
    ID,
    FIO,
    SALARY,
    DEPARTMENT;
    public static final EnumSet<FieldFlags> ALL_FIELDS = EnumSet.allOf(FieldFlags.class);
}
