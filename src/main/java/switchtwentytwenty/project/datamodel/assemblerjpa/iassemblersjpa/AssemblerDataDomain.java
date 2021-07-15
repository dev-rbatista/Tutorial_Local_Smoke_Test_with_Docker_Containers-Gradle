package switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa;

public interface AssemblerDataDomain<T, K> {

    T toData(K k);

}
