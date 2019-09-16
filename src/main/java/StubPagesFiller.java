public interface StubPagesFiller {
    static void fill(PagesController ctrl) {
        ctrl.addPage("1", "one");
        ctrl.addPage("2", "two");
        ctrl.addPage("3", "three");
        ctrl.changeDay();
        ctrl.addPage("4", "four");
        ctrl.removePage("2");
        ctrl.editPage("3", "eerht");
    }
}
