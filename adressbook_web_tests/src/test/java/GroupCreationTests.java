import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void canCreateGroup() {
        opensGroupsPage();
        createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        opensGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        opensGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
