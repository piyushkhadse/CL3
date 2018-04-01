#include<iostream>
#include<omp.h>
#include<stdlib.h>
using namespace std;
int n;								//stores the no. of elements
int partition(int a[],int p, int r);				//partition the array into two 
int binarysearch(int a[],int low,int high,int key);		//to search the key in the sorted array
void quicksort(int a[], int p, int r)
{
	int q;

	if(p<r){
		
		q=partition(a,p,r);				//partition function returns the pivot element
		#pragma omp parallel sections
		{
			#pragma omp section
			{

				quicksort(a,p,q-1);
			}

			#pragma omp section
			{
				quicksort(a,q+1,r);
			}

		}
	}
	

}


int partition(int a[],int p, int r)
{
	int i=p-1;
	int x=a[r];
	int temp;

	for(int j=p; j<=r-1; j++){

		if(a[j]<=x){
			i++;
			temp=a[i];
			a[i]=a[j];
			a[j]=temp;
			

		}
	}
	temp=a[i+1];
	a[i+1]=a[r];
	a[r]=temp;

	cout<<"\n ";
	for(int j=0; j<n; j++){
		cout<<"\t "<<a[j];
	}

return i+1;

}



int binarysearch(int a[],int low,int high,int key)
{
	int mid=(low+high)/2;
	
	if(key < a[mid])
	{
		high=mid-1;
		
	}
	else if(key > a[mid])
	{
		low=mid+1;
		
	}
	else if(key==a[mid])
	{
		return mid+1;	
	}
	else if(low>high)
	{	cout<<"\n Key not found!";
		exit(0);
		
	}
	else{
		}

	binarysearch(a,low,high,key);
	
}

int main()
{
	
	
	
	cout<<"\n Enter no. of elements : ";
	cin>>n;

	int a[n],key;
	
	//cout<<"\n Enter the elements : ";
	for(int i=0; i<n; i++){
		//cin>>a[i];
	   a[i]=rand()%1000;

	}

	cout<<"\n Unsorted array : ";
	for(int i=0; i<n; i++){
		cout<<a[i]<<"\t";
	}
	cout<<"\n";

	quicksort(a,0,n-1);				//invoking the quicksort function

	cout<<"\n Sorted array : ";
	for(int i=0; i<n; i++){
		cout<<a[i]<<"\t";

	}

	cout<<"\nEnter the key : ";
	cin>>key;
	
	int pos=binarysearch(a,0,n-1,key);
	cout<<"\n"<<key<<" found at index "<<pos;
cout<<"\n";
return 0;
}
