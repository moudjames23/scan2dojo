

## Scan2dojo
**scan2dojo** is a CLI (Command Line Interface) allowing you to easily upload scan reports to [DefectDojo](https://github.com/DefectDojo/django-DefectDojo). It can be used both on the command line and integrated into a CI/CD pipeline to automate the import of your security reports.

## Features

- **Configuration**: Configure the endpoint and API key for integration with DefectDojo.
- **Product type Creation**: Create new products type in DefectDojo.
-  **Product Creation**: Create new products in DefectDojo.
- **Creating engagement**: Create new engagement associated with products.
- **Scan Import**: Import scan results into DefectDojo with advanced configuration options.

### Demo
You can use the online demo of [DefectDojo](https://hub.docker.com/r/defectdojo/defectdojo-django) which is available [here](https://demo.defectdojo.org/)

> User: **admin**
> Password: **1Defectdojo@demo#appsec**

You can retrieve your Api Key [here](https://demo.defectdojo.org/api/key-v2) after authenticating

## Installation

### Via Docker

You can use Docker to run `scan2dojo` in interactive mode (`-it`) and automatically remove the container after use with the `--rm` option.


     docker run --rm -it moudjames23/scan2dojo:1.0.0



You can also mount a volume to specify the location of your scan results when running the Docker image. For example, if your scan results are located at `/Users/Moud/Libs/trivy.json`, you can mount it like this:

    docker run --rm -it -v /Users/Moud/Libs:/data moudjames23/scan2dojo:1.0.0

You can directly execute a commande like this:

    docker run -v /Users/Moud/Libs:/dat moudjames23/scan2dojo:1.0.0 import --scanType "Trivy Scan" --file /data/trivy.json --productName "Scan2dojo" --engagementName "Release" --minimumSeverity High

> Before running this command, make sure that the product "Scan2dojo" exists and that the "Release" engagement also exists.


### MacOs or Linux


     curl  -L  https://github.com/moudjames23/scan2dojo/releases/download/v1.0.0/install.sh  |  bash 


## Usage

### Show version

To view the current version of `scan2dojo`:


     scan2dojo version  

### Show help

To display the custom help message, use:


     scan2dojo help  

### Configure endpoint and API key

This command sets the endpoint and API key that will be used by the application to communicate with the Scan2Dojo service


     scan2dojo configure --endpoint https://api.example.com --apiKey your-api-key

- --***endpoint***: The url of the defectdojo service endpoint
-  --***apiKey***: The api key used to authenticate requests to the endpoint


### Create a new product  type
his command allows the user to create a new product type by providing a name,  description, and two boolean flags to specify if the product type is critical and/or considered a key type.

    scan2dojo create:product-type --name Business --description "Product type description" --critical true --key false

- --***name***: The name of the product type. This is a required parameter provided by the user

- --***description***: A brief description of the product type. This is a required parameter provided by the user

- --***critical:*** Flag indicating whether the product type is considered critical. This is an optional parameter  with a default value of true
-  --***key***: Flag indicating whether the product type is considered a key type or primary feature. This is an optional parameter with a default value of "true

### Create a new product

This command allows you to create a new product by providing the necessary details  such as the product name, description, type ID, and SLA configuration. If the SLA  configuration is set to 0, it will be defaulted to 1.


     scan2dojo create:product --name "Product Name" --description "Product Description" --typeId 1 --slaConfiguration 1  



- --***name***: The name of the product. This is a required field.
- --***description***: A brief description of the product. This is a required field.
- --***typeId***: The ID representing the type of the product. This is a required field.
- --***slaConfiguration***: The Service Level Agreement (SLA) configuration ID for the product. The default value is 1

### Create an engagement

This command allows the user to create a new engagement by providing necessary details such as the engagement name, description, start and end dates, and the associated product ID. If the start and end dates are not provided, they default to today's date and one year from today, respectively.



    scan2dojo create:engagement --name "Engagement name" --description "Engagement description" --start "2024-01-01" --end "2024-12-31" --productId 2

- --***name*** The name of the engagement. This is a required field.
- --***description*** A brief description of the engagement. This is a required field.
- --***start*** The start date of the engagement in the format yyyy-MM-dd. Defaults to today's date if not provided.
- --***end*** The end date of the engagement in the format yyyy-MM-dd. Defaults to one year from today's date if not provided.
- --***productId*** The ID of the product associated with this engagement. This is a required field.



### Import a scan result

This command allows the user to import a scan result into DefectDojo by specifying details such as the scan type, the file containing the scan results, the name of the product, the name of the engagement, and the minimum severity level of vulnerabilities to be included.


     scan2dojo import --scanType "Trivy Scan" --file /path/to/scan_result.json --productName "Product Name" --engagementName "Engagement Name" --minimumSeverity High  

- --***scanType*** The type of the scan. This should correspond to the scanner used.
- --***file*** The path to the scan result file to be imported.
- ***--productName*** The name of the product in DefectDojo to which the scan result belongs.
- ***--engagementName*** The name of the engagement in DefectDojo under which the scan result will be recorded.
- ***--minimumSeverity*** The minimum severity of findings to be imported. Possible values are **Info**, **Low**, **Medium**, **High**
  and **Critical**

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
