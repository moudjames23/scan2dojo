

## Scan2dojo
**scan2dojo** is a CLI (Command Line Interface) allowing you to easily upload scan reports to [DefectDojo](https://github.com/DefectDojo/django-DefectDojo). It can be used both on the command line and integrated into a CI/CD pipeline to automate the import of your security reports.

## Features

- **Configuration**: Configure the endpoint and API key for integration with DefectDojo.
- **Product Creation**: Create new products in DefectDojo.
- **Creating engagement**: Create new engagement associated with products.
- **Scan Import**: Import scan results into DefectDojo with advanced configuration options.

## Installation

### Via Docker

You can use docker to install scan2dojo:


     docker pull moudjames23/scan2dojo:v1.0.0

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

In interactive mode, you can just type:

    scan2dojo configure

Then enter the endpoint and apiKey

### Create a product

To create a new product in DefectDojo:


     scan2dojo create:product --name "Product Name" --description "Product Description" --typeId 1 --slaConfiguration 1  



- [ ] You will have to reassure yourself of the existence of the
  product type before and the **slaConfiguration** is optional

### Create an engagement

To create a new engagement in DefectDojo:



    scan2dojo create:engagement --name "Engagement name" --description "Engagement description" --start "2024-01-01" --end "2024-12-31" --productId 2

- [ ] ***start*** is  optional and by default it will take the current date
- [ ] ***end*** is optional and by default it will take the date in one year
- [ ] ***productId*** is the id of the product to which we want to link this engagement

### Import a scan result

To import a scan result into DefectDojo:


     scan2dojo import --scanType "Trivy Scan" --file /path/to/scan_result.json --productName "Product Name" --engagementName "Engagement Name" --minimumSeverity High  

- [ ] ***--scanType*** allows you to define the type of scan supported by defectdojo. You can find the list
  [here](https://documentation.defectdojo.com/integrations/parsers/file/)
- [ ] ***--file*** the absolute path of the scan result
- [ ] ***--productName***  product's name
- [ ] ***--engagementName***  engagement's name
- [ ] ***--minimumSeverity*** List of possible values: **Info**, **Low**, **Medium**, **High**, **Critical**

## CI/CD integration

### GitHub Actions
**Installation**

    - name: install scan2dojo
      run: |
        curl -L https://github.com/moudjames23/scan2dojo/releases/download/v1.0.0/install.sh | bash

**Configuration**
Configure your endpoint and your apikey. I chose to define them as secrets but you can of course display them clearly

    - name: Configure scan2dojo CLI
      run: |
        scan2dojo configure --endpoint ${{ secrets.SCAN2DOJO_ENDPOINT }} --apiKey ${{ secrets.SCAN2DOJO_APIKEY }}

**Import**

    - name: Import Scan Results to DefectDojo 
      run: scan2dojo import --scanType "Trivy Scan" --file path_to_security_scan --productName Scan2Dojo --engagementName Release --minimumSeverity High


### GitLab CI



    scan-and-upload:
	    stage: scan-and-upload
	    image: ubuntu:latest
	    script:
		    - echo "Downloading scan2dojo"
		    - curl -L https://github.com/moudjames23/scan2dojo/releases/download/v1.0.0/install.sh | bash
		    - echo "Configuring scan2dojo CLI"
		    - scan2dojo configure --endpoint your_endpoint --apiKey your_apikey
		    - echo "Importing Scan Results to DefectDojo"
		    - scan2dojo import --scanType "Trivy Scan" --file path_to_scan_result --productName Scan2Dojo --engagementName GitLab --minimumSeverity High
	    only:
	        - main


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Any issues, please [report here](https://github.com/moudjames23/scan2dojo/issues)

## License
[MIT](https://choosealicense.com/licenses/mit/)
