package git;

import git.enums.VersionLevel;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AutoTagMasterCommitTest {

    @Test
    public void getNextTagTest() {

        Assert.assertEquals("1.2.4", AutoTagMasterCommit.getNextTag("1.2.3", VersionLevel.PATCH));
        Assert.assertEquals("1.3.0", AutoTagMasterCommit.getNextTag("1.2.3", VersionLevel.MINOR));
        Assert.assertEquals("2.0.0", AutoTagMasterCommit.getNextTag("1.2.3", VersionLevel.MAJOR));

    }

    @Test
    public void validateTest() {
        Assert.assertTrue(AutoTagMasterCommit.validate(new String[]{}));
        Assert.assertTrue(AutoTagMasterCommit.validate(new String[]{"major"}));
        Assert.assertTrue(AutoTagMasterCommit.validate(new String[]{"minor"}));
        Assert.assertTrue(AutoTagMasterCommit.validate(new String[]{"patch"}));

        Assert.assertFalse(AutoTagMasterCommit.validate(new String[]{""}));
        Assert.assertFalse(AutoTagMasterCommit.validate(new String[]{"asdfg"}));
    }

    @Test
    public void getValidTagsTest() {
        Assert.assertEquals(Arrays.asList("1.2.3", "10.3.2", "1.0.0"),
                AutoTagMasterCommit.getValidTags(new String[]{"a1.0", "b-2.1.0", "1.2.3", "10.3.2", "1.0.0"}));
    }

    @Test
    public void sortTest() {
        List<String> list = Arrays.asList("10.0.0", "1.0.0", "1.0.1", "1.1.1", "10.0.1", "10.1.1", "2.3.4", "12.0.0");
        AutoTagMasterCommit.sort(list);
        System.out.println(list);
        Assert.assertEquals(
                Arrays.asList("12.0.0", "10.1.1", "10.0.1", "10.0.0", "2.3.4", "1.1.1", "1.0.1", "1.0.0"),
                list);
    }
}
