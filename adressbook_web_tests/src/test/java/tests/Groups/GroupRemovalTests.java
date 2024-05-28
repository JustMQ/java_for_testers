package tests.Groups;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData(
                        "",
                        properties.getProperty("web.groupname"),
                        properties.getProperty("web.groupheader"),
                        properties.getProperty("web.groupfooter")));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Checking precondition", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData(
                    "",
                    properties.getProperty("web.groupname"),
                    properties.getProperty("web.groupheader"),
                    properties.getProperty("web.groupfooter")));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());

    }
}
