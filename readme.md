
## Scan2dojo
**scan2dojo** is a CLI (Command Line Interface) allowing you to easily upload scan reports to [DefectDojo](https://github.com/DefectDojo/django-DefectDojo). It can be used both on the command line and integrated into a CI/CD pipeline to automate the import of your security reports.

## Features

- **Configuration**: Configure the endpoint and API key for integration with DefectDojo.
- **Product Creation**: Create new products in DefectDojo.
- **Creating commitments**: Create new commitments associated with products.
- **Scan Import**: Import scan results into DefectDojo with advanced configuration options.

## Facility

### Via Docker

The recommended way to use `scan2dojo` is through Docker. Make sure you have Docker installed on your machine.

    docker pull moudjames23/scan2dojo:v1

### MacOs or Linux

    curl  -L  https://github.com/moudjames23/scan2dojo/releases/download/v1.0.0/install.sh  |  bash

### Windows

## Usage

### Show version

To view the current version of `scan2dojo`:

    scan2dojo version

### Show help

To display the custom help message, use:

    scan2dojo help

### Configure endpoint and API key

Before using `scan2dojo`, you need to configure it to connect to your DefectDojo instance:

    scan2dojo configure --endpoint https://api.example.com --apiKey your-api-key

### Create a product

To create a new product in DefectDojo:

    scan2dojo create:product --name "Product Name" --description "Product Description" --typeId 1 --slaConfiguration 1

### Create an engagement

To create a new engagement in DefectDojo:

    scan2dojo create:engagement --name "Engagement name" --description "Engagement description" --start "2024-01-01" --end "2024-12-31" --productId 123

### Import a scan result

To import a scan result into DefectDojo:

    scan2dojo import --scanType "Trivy Scan" --file /path/to/scan_result.json --productName "Product Name" --engagementName "Engagement Name" --minimumSeverity High

## CI/CD integration

### GitHub Actions

### GitLab CI

###Jenkins
