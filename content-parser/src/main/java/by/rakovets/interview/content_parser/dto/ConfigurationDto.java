package by.rakovets.interview.content_parser.dto;

public class ConfigurationDto {
    private String pathForSaving;
    private String pathForAssets;
    private ResourceDto[] resources;

    public String getPathForSaving() {
        return pathForSaving;
    }

    public void setPathForSaving(String pathForSaving) {
        this.pathForSaving = pathForSaving;
    }

    public ResourceDto[] getPages() {
        return resources;
    }

    public void setResources(ResourceDto[] resources) {
        this.resources = resources;
    }

    public String getPathForAssets() {
        return pathForAssets;
    }

    public void setPathForAssets(String pathForAssets) {
        this.pathForAssets = pathForAssets;
    }
}
